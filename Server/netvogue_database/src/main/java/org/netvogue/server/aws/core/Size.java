package org.netvogue.server.aws.core;

public enum Size {
	S1 (20,16),
	S2 (57,52),
	S3 (149,93),
	S4 (184,138),
	S5 (4,4),
	S6 (4,4),
	S7 (4,4),
	S8 (4,4),
	S9 (4,4),
	S10 (4,4);
	
	private final int width;   // in pixels
    private final int height; // in pixels
    
	Size(int width,int height ) {
		this.width = width;
		this.height = height;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
