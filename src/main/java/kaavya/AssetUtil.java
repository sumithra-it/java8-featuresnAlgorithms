package kaavya;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import kaavya.Asset.AssetType;

public class AssetUtil {

public static int totalStockValue(List<Asset> assets, Predicate<Asset> assetSelector) {

return assets.stream()
		.filter(assetSelector)
		.mapToInt(Asset :: getValue)
		.sum();

}
public static void main(String[] args) {
// init Asset objects
	final List<Asset> assetList = Arrays.asList(new Asset(AssetType.BOND, 3000),
									new Asset(AssetType.BOND, 7000), new Asset(AssetType.BOND, 5000),
									new Asset(AssetType.STOCK, 10000), new Asset(AssetType.STOCK, 13000));

		//print total bond values
		System.out.println("print total bond values " + 
		totalStockValue(assetList, asset -> asset.getType() == AssetType.BOND));
		
		System.out.println("print total stock values " + 
		totalStockValue(assetList, asset -> asset.getType() == AssetType.STOCK));
		
		System.out.println("print total of all asset values " + 
		totalStockValue(assetList, asset -> true));
	}

}
