package org.netvogue.server.neo4japi.common;

import org.netvogue.server.neo4japi.common.ProductLineSizes;

public enum ProductLines {
	WOMENS_RTW(	"Womens RTW",	CategoryType.APPAREL, ProductLineSizes.Handbag),
	MENS_RTW(	"Mens RTW", 	CategoryType.APPAREL, ProductLineSizes.Handbag),
	DENIM(		"Denim", 		CategoryType.APPAREL, ProductLineSizes.Handbag),
	OUTER_WEAR(	"Outerwear", 	CategoryType.APPAREL, ProductLineSizes.Handbag),
	ACTIVE_WEAR("Activewear", 	CategoryType.APPAREL, ProductLineSizes.Handbag),//Yoga
	LINGERIE(	"Lingerie", 	CategoryType.APPAREL, ProductLineSizes.Handbag),
	SWIMWEAR(	"Swimwear", 	CategoryType.APPAREL, ProductLineSizes.Handbag),
	KIDS(		"Kids", 		CategoryType.APPAREL, ProductLineSizes.Handbag),
	WOMENS_SHOE("Womens Shoe", 	CategoryType.SHOES,	  ProductLineSizes.Handbag),
	MENS_SHOE(	"Mens Shoe", 	CategoryType.SHOES,   ProductLineSizes.Handbag),
	MENS_BAGS(	"Mens Bags", 	CategoryType.HANDBAGS,ProductLineSizes.Handbag),
	HANDBAGS(	"Handbags", 	CategoryType.HANDBAGS,ProductLineSizes.Handbag),
	WATCHES(	"Watches", 		CategoryType.WATCHES, ProductLineSizes.Handbag),
	JEWELLERY(	"Jewelry", 		CategoryType.OTHERS,  ProductLineSizes.Handbag),
	HATS(		"Hats", 		CategoryType.OTHERS,  ProductLineSizes.Handbag),
	LUGGAGE(	"Luggage", 		CategoryType.OTHERS,  ProductLineSizes.Handbag),
	GIFTS(		"Gifts", 		CategoryType.OTHERS,  ProductLineSizes.Handbag),
	CANDLES(	"Candles", 		CategoryType.OTHERS,  ProductLineSizes.Handbag);

	private  String desc;
	private  CategoryType category;
	private  ProductLineSizes sizes;
	
	ProductLines(String desc, CategoryType category, ProductLineSizes productSizes) {
		this.desc 		= desc;
		this.category	= category;
		this.sizes 		= productSizes;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public CategoryType getCategoryType() {
		return category;
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
