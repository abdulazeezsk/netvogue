package org.netvogue.server.neo4japi.common;

import org.netvogue.server.neo4japi.common.ProductLineSizes;

public enum ProductLines {
	WOMENS_RTW(	"Womens RTW",	CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	MENS_RTW(	"Mens RTW", 	CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	DENIM(		"Denim", 		CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	OUTER_WEAR(	"Outerwear", 	CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	ACTIVE_WEAR("Activewear", 	CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	LINGERIE(	"Lingerie", 	CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	SWIMWEAR(	"Swimwear", 	CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	KIDS(		"Kids", 		CategoryType.APPAREL, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	WOMENS_SHOE("Womens Shoe", 	CategoryType.SHOES,	  new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	MENS_SHOE(	"Mens Shoe", 	CategoryType.SHOES,   new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	MENS_BAGS(	"Mens Bags", 	CategoryType.HANDBAGS,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	HANDBAGS(	"Handbags", 	CategoryType.HANDBAGS,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	WATCHES(	"Watches", 		CategoryType.WATCHES, new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	JEWELLERY(	"Jewelry", 		CategoryType.OTHERS,  new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	HATS(		"Hats", 		CategoryType.OTHERS,  new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	LUGGAGE(	"Luggage", 		CategoryType.OTHERS,  new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	GIFTS(		"Gifts", 		CategoryType.OTHERS,  new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	CANDLES(	"Candles", 		CategoryType.OTHERS,  new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	ANKLETS(	"Anklets", 		CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	BANGLES(	"Bangles", 		CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	BRACELETS(	"Bracelets", 	CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	EARRINGS(	"Earrings", 	CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	NECKLACES(	"Necklaces", 	CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	PENDANT_SETS("Pendant Sets",CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	PENDANTS(	"PENDANTS", 	CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	RINGS(		"Rings", 		CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE}),
	TOE_RINGS(	"Toe Rings", 	CategoryType.JEWELLERY,new ProductLineSizes[]{ProductLineSizes.SMALL, ProductLineSizes.MEDIUM, ProductLineSizes.LARGE});

	private  String desc;
	private  CategoryType category;
	private  ProductLineSizes[] sizes;
	
	ProductLines(String desc, CategoryType category, ProductLineSizes[] productSizes) {
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
	
	public ProductLineSizes[] getProductLineSizes() {
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
