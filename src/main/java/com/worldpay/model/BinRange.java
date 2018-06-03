package com.worldpay.model;

public class BinRange implements Comparable<BinRange> {
	
	private Integer startIIN;
	private Integer endIIN;
	private Integer sizeIIN;
	private Integer issuerCode;
	
	public BinRange(Integer startIIN, Integer endIIN, Integer sizeIIN) {
		this(startIIN,endIIN,sizeIIN,null);
	}
		
	public BinRange(Integer startIIN, Integer endIIN, Integer sizeIIN, Integer issuerCode) {
		super();
		this.startIIN = startIIN;
		this.endIIN = endIIN;
		this.sizeIIN = sizeIIN;
		this.issuerCode = issuerCode;
	}



	public Integer getStartIIN() {
		return startIIN;
	}
	public void setStartIIN(Integer startIIN) {
		this.startIIN = startIIN;
	}
	public Integer getEndIIN() {
		return endIIN;
	}
	public void setEndIIN(Integer endIIN) {
		this.endIIN = endIIN;
	}
	public Integer getSizeIIN() {
		return sizeIIN;
	}
	public void setSizeIIN(Integer sizeIIN) {
		this.sizeIIN = sizeIIN;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startIIN == null) ? 0 : startIIN.hashCode());
		result = prime * result + ((endIIN == null) ? 0 : endIIN.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinRange other = (BinRange) obj;
	
		if (startIIN == null) {
			if (other.startIIN != null)
				return false;
		} else if (!startIIN.equals(other.startIIN))
			return false;

		if (endIIN == null) {
			if (other.endIIN != null)
				return false;
		} else if (!endIIN.equals(other.endIIN))
			return false;
		return true;
	}
	public Integer getIssuerCode() {
		return issuerCode;
	}
	public void setIssuerCode(Integer issuerCode) {
		this.issuerCode = issuerCode;
	}
	@Override
	public String toString() {
		return "BinRange [startIIN=" + startIIN + ", endIIN=" + endIIN + ", sizeIIN=" + sizeIIN + ", issuerCode="
				+ issuerCode + "]";
	}

	@Override
	public int compareTo(BinRange o) {
		int resultCompareIIN = ((this.startIIN < o.startIIN)) ? 1:((this.startIIN > o.startIIN)) ? -1 : 0;
		
		//means startIIN for both are same
		if ( resultCompareIIN == 0){
			
			if ( this.endIIN < o.endIIN){
				resultCompareIIN = -1; 
			}else if ( this.endIIN > o.endIIN ){
				resultCompareIIN = 1;
			}else{
				resultCompareIIN = 0;
			}
		}
		return resultCompareIIN;
	}
	
	

}
