package com.bmasmoudi.tutos.enums.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.bmasmoudi.tutos.model.enums.PrenomsEnum;
import com.bmasmoudi.tutos.model.enums.transformer.WDIEnumTransformer;

public class WDIEnumTransformerTest {

	@Test
	public void should_transform_basicPersmission_to_CarrierEnum() {
		PrenomsEnum carrierCode = WDIEnumTransformer.forEnum(PrenomsEnum.class).transform("AL");
		assertEquals(PrenomsEnum.AL, carrierCode);
	}

	@Test
	public void should_return_null_CarrierCodeEnum() {
		PrenomsEnum carrierCode = WDIEnumTransformer.forEnum(PrenomsEnum.class).transform("XXXXXXX");
		assertNull(carrierCode);
	}

}
