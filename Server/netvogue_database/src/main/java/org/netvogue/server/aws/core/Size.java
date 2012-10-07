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
	S10 (4,4),
	GAdd(220, 150),
	GThumb(220, 306),
	GLeft(130, 100),
	GMain(450, 677),
	PCAdd(220, 150),
	PCThumb(220, 306),
	PCLeft(90, 72),
	PCMain(450, 677),
	EAdd(210, 300),
	EThumb(220, 306),
	ELeft(130, 100),
	EMain(450, 677),
	CAdd(210, 300),
	CThumb(231, 306),
	CLeft(130, 100),
	CMain(550, 550),
	SAdd(130, 151),
	SThumb(220, 320),
	SLeft(130, 100),
	SMain(550, 550),
	LAdd(130, 151),
	LThumb(220, 320),
	LLeft(130, 100),
	LMain(550, 550);
	
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
