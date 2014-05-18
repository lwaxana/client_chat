package client;

public class Droits {

	private String nomChannel;
	private int droits;
	
	public Droits(String nomChannel, int droits){
		this.nomChannel = nomChannel;
		this.droits = droits;
		
	}

	/**
	 * @return the nomChannel
	 */
	public String getNomChannel() {
		return nomChannel;
	}

	/**
	 * @return the droits
	 */
	public int getDroits() {
		return droits;
	}

	/**
	 * @param nomChannel the nomChannel to set
	 */
	public void setNomChannel(String nomChannel) {
		this.nomChannel = nomChannel;
	}

	/**
	 * @param droits the droits to set
	 */
	public void setDroits(int droits) {
		this.droits = droits;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Droits [nomChannel=" + nomChannel + ", droits=" + droits + "]";
	}
	
	
	
}
