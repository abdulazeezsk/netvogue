package org.netvogue.server.neo4japi.common;

import org.netvogue.server.neo4japi.common.ProductLineSizes;

public enum ProductLines {
	WOMENS_RTW("Womens RTW", ProductLineSizes.Handbag), 
	WOMENS_SHOE("Womens Shoe", ProductLineSizes.Handbag),
	DENIM("Denim", ProductLineSizes.Handbag),
	OUTER_WEAR("Outerwear", (ProductLineSizes.Handbag)),
	ACTIVE_WEAR("Activewear", (ProductLineSizes.Handbag)),//Yoga
	MENS_RTW("Mens RTW", (ProductLineSizes.Handbag)),
	MENS_SHOE("Mens Shoe", (ProductLineSizes.Handbag)),
	MENS_BAGS("Mens Bags", (ProductLineSizes.Handbag)),
	HANDBAGS("Handbags", (ProductLineSizes.Handbag)),
	LINGERIE("Lingerie", (ProductLineSizes.Handbag)),
	JEWELLERY("Jewelry", (ProductLineSizes.Handbag)),
	SWIMWEAR("Swimwear", (ProductLineSizes.Handbag)),
	KIDS("Kids", (ProductLineSizes.Handbag)),
	WATCHES("Watches", (ProductLineSizes.Handbag)),
	HATS("Hats", (ProductLineSizes.Handbag)),
	LUGGAGE("Luggage", (ProductLineSizes.Handbag)),
	GIFTS("Gifts", (ProductLineSizes.Handbag)),
	CANDLES("Candles", (ProductLineSizes.Handbag));

	private  String desc;
	private  ProductLineSizes sizes;
	
	ProductLines(String desc, ProductLineSizes productSizes) {
		this.desc 	= desc;
		this.sizes 	= productSizes;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public ProductLineSizes getProductLineSizes() {
		return sizes;
	}
	
	public static ProductLines getValueOf(String desc) {
		//for (ProductLines element : EnumSet.allOf(ProductLines.class)) {
		for (ProductLines element : ProductLines.values()) {
            if (desc.equals(element.getDesc())) {
                return element;
            }
        }
		return null;
		
	}
}
