package com.bmasmoudi.tutos.docx;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Document;
import org.junit.Test;

public class DocxManipulator {

	private static final String MAIN_DOCUMENT_PATH = "word/document.xml";
	private static final String TEMPLATE_DIRECTORY_ROOT = "d:/";

	@Test
	public void testEquals() throws Docx4JException, JAXBException {

		String pathname = "d:/docx/test.docx";
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(pathname));

		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

		Document wmlDocumentEl1 = documentPart.getJaxbElement();
		String xml = XmlUtils.marshaltoString(wmlDocumentEl1, true);
		java.util.HashMap<String, String> mappings = new HashMap<String, String>();
		mappings.put("key1", "Flowers");
		Object obj = XmlUtils.unmarshallFromTemplate(xml, mappings);

		MainDocumentPart wordDocumentPart = new MainDocumentPart();
		// Put the content in the part
		wordDocumentPart.setJaxbElement((Document) obj);
		// Add the main document part to the package relationships
		// (creating it if necessary)
		wordMLPackage.addTargetPart(wordDocumentPart);

		SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
		saver.save(pathname);

		// generateAndSendDocx("test.zip", Maps.newHashMap());
	}

	@Test
	public void testEquals2() throws Exception {

		String pathname = "d:/docx/test.docx";
		WordprocessingMLPackage mlp = WordprocessingMLPackage.load(new File(pathname));
		replaceText(mlp.getMainDocumentPart());
	}

	static void replaceText(final ContentAccessor c) throws Exception {
		for (Object p : c.getContent()) {
			if (p instanceof ContentAccessor) {
				replaceText((ContentAccessor) p);
			} else if (p instanceof JAXBElement) {
				Object v = ((JAXBElement) p).getValue();

				if (v instanceof ContentAccessor) {
					replaceText((ContentAccessor) v);
				} else if (v instanceof org.docx4j.wml.Text) {
					org.docx4j.wml.Text t = (org.docx4j.wml.Text) v;
					String text = t.getValue();

					if (text != null) {
						t.setSpace("preserve"); // needed?
						t.setValue(replaceParams(text));
					}
				}
			}
		}
	}

	static Pattern paramPatern = Pattern.compile("(?i)(\\$\\{([\\w\\.]+)\\})");

	static String replaceParams(final String text) {
		Matcher m = paramPatern.matcher(text);

		if (!m.find()) {
			return text;
		}

		StringBuffer sb = new StringBuffer();
		String param, replacement;

		do {
			param = m.group(2);

			if (param != null) {
				replacement = getParamValue(param);
				m.appendReplacement(sb, replacement);
			} else {
				m.appendReplacement(sb, "");
			}
		} while (m.find());

		m.appendTail(sb);
		return sb.toString();
	}

	static String getParamValue(final String name) {
		// replace from map or something else
		return "Bilel";
	}

	/* PUBLIC METHODS */

	/**
	 * Generates .docx document from given template and the substitution data
	 * 
	 * @param templateName
	 *            Template data
	 * @param substitutionData
	 *            Hash map with the set of key-value pairs that represent substitution data
	 * @return
	 */
	public static Boolean generateAndSendDocx(final String templateName, final Map<String, String> substitutionData) {

		String templateLocation = TEMPLATE_DIRECTORY_ROOT + templateName;

		String userTempDir = UUID.randomUUID().toString();
		userTempDir = TEMPLATE_DIRECTORY_ROOT + userTempDir + "/";

		try {

			// Unzip .docx file
			unzip(new File(templateLocation), new File(userTempDir));

			// Change data
			changeData(new File(userTempDir + MAIN_DOCUMENT_PATH), substitutionData);

			// Rezip .docx file
			zip(new File(userTempDir), new File(userTempDir + templateName));

			// Send HTTP response
			// sendDOCXResponse(new File(userTempDir + templateName), templateName);

			// Clean temp data
			deleteTempData(new File(userTempDir));
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			return false;
		}

		return true;
	}

	/* PRIVATE METHODS */

	/**
	 * Unzipps specified ZIP file to specified directory
	 * 
	 * @param zipfile
	 *            Source ZIP file
	 * @param directory
	 *            Destination directory
	 * @throws IOException
	 */
	private static void unzip(final File zipfile, final File directory) throws IOException {

		ZipFile zfile = new ZipFile(zipfile);
		Enumeration<? extends ZipEntry> entries = zfile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			File file = new File(directory, entry.getName());
			if (entry.isDirectory()) {
				file.mkdirs();
			} else {
				file.getParentFile().mkdirs();
				InputStream in = zfile.getInputStream(entry);
				try {
					copy(in, file);
				} finally {
					in.close();
				}
			}
		}
	}

	/**
	 * Substitutes keys found in target file with corresponding data
	 * 
	 * @param targetFile
	 *            Target file
	 * @param substitutionData
	 *            Map of key-value pairs of data
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void changeData(final File targetFile, final Map<String, String> substitutionData) throws IOException {

		BufferedReader br = null;
		String docxTemplate = "";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				docxTemplate = docxTemplate + temp;
			}
			br.close();
			targetFile.delete();
		} catch (IOException e) {
			br.close();
			throw e;
		}

		Iterator substitutionDataIterator = substitutionData.entrySet().iterator();
		while (substitutionDataIterator.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) substitutionDataIterator.next();
			if (docxTemplate.contains(pair.getKey())) {
				if (pair.getValue() != null) {
					docxTemplate = docxTemplate.replace(pair.getKey(), pair.getValue());
				} else {
					docxTemplate = docxTemplate.replace(pair.getKey(), "NEDOSTAJE");
				}
			}
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);
			fos.write(docxTemplate.getBytes("UTF-8"));
			fos.close();
		} catch (IOException e) {
			fos.close();
			throw e;
		}
	}

	/**
	 * Zipps specified directory and all its subdirectories
	 * 
	 * @param directory
	 *            Specified directory
	 * @param zipfile
	 *            Output ZIP file name
	 * @throws IOException
	 */
	private static void zip(File directory, final File zipfile) throws IOException {

		URI base = directory.toURI();
		Deque<File> queue = new LinkedList<File>();
		queue.push(directory);
		OutputStream out = new FileOutputStream(zipfile);
		Closeable res = out;

		try {
			ZipOutputStream zout = new ZipOutputStream(out);
			res = zout;
			while (!queue.isEmpty()) {
				directory = queue.pop();
				for (File kid : directory.listFiles()) {
					String name = base.relativize(kid.toURI()).getPath();
					if (kid.isDirectory()) {
						queue.push(kid);
						name = name.endsWith("/") ? name : name + "/";
						zout.putNextEntry(new ZipEntry(name));
					} else {
						if (kid.getName().contains(".docx")) {
							continue;
						}
						zout.putNextEntry(new ZipEntry(name));
						copy(kid, zout);
						zout.closeEntry();
					}
				}
			}
		} finally {
			res.close();
		}
	}

	/**
	 * Sends HTTP Response containing .docx file to Client
	 * 
	 * @param generatedFile
	 *            Path to generated .docx file
	 * @param fileName
	 *            File name of generated file that is being presented to user
	 * @throws IOException
	 */
	// private static void sendDOCXResponse(final File generatedFile, final String fileName) throws IOException {
	//
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// ExternalContext externalContext = facesContext.getExternalContext();
	// HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
	//
	// BufferedInputStream input = null;
	// BufferedOutputStream output = null;
	//
	// response.reset();
	// response.setHeader("Content-Type", "application/msword");
	// response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	// response.setHeader("Content-Length", String.valueOf(generatedFile.length()));
	//
	// input = new BufferedInputStream(new FileInputStream(generatedFile), 10240);
	// output = new BufferedOutputStream(response.getOutputStream(), 10240);
	//
	// byte[] buffer = new byte[10240];
	// for (int length; (length = input.read(buffer)) > 0;) {
	// output.write(buffer, 0, length);
	// }
	//
	// output.flush();
	// input.close();
	// output.close();
	//
	// // Inform JSF not to proceed with rest of life cycle
	// facesContext.responseComplete();
	// }

	/**
	 * Deletes directory and all its subdirectories
	 * 
	 * @param file
	 *            Specified directory
	 * @throws IOException
	 */
	public static void deleteTempData(final File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
			} else {
				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					// recursive delete
					deleteTempData(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			// if file, then delete it
			file.delete();
		}
	}

	private static void copy(final InputStream in, final OutputStream out) throws IOException {

		byte[] buffer = new byte[1024];
		while (true) {
			int readCount = in.read(buffer);
			if (readCount < 0) {
				break;
			}
			out.write(buffer, 0, readCount);
		}
	}

	private static void copy(final File file, final OutputStream out) throws IOException {
		InputStream in = new FileInputStream(file);
		try {
			copy(in, out);
		} finally {
			in.close();
		}
	}

	private static void copy(final InputStream in, final File file) throws IOException {
		OutputStream out = new FileOutputStream(file);
		try {
			copy(in, out);
		} finally {
			out.close();
		}
	}

}
