package kaavya;

public class Asset {
public enum AssetType {BOND, STOCK};
private AssetType type;
private int value;
public Asset(AssetType t, int vl) {
type = t;
value = vl;
}
public AssetType getType() { return type; }
public int getValue() { return value; }
}