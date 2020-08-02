package kaavya;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kaavya.Asset.AssetType;

class AssertUtilTest {
	 List<Asset> assetList;
	@BeforeEach
	void setUp() throws Exception {
		assetList = Arrays.asList(new Asset(AssetType.BOND, 3000),
				new Asset(AssetType.BOND, 7000), new Asset(AssetType.BOND, 5000),
				new Asset(AssetType.STOCK, 10000), new Asset(AssetType.STOCK, 13000));
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTotalBond() {
		Assertions.assertTrue(AssetUtil.totalStockValue(assetList, t -> (t.getType() == AssetType.BOND)) == 15000, 
				() -> "Total bond type values is expected to be 15K");
	}

}
