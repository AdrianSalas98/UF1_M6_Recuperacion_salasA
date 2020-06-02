import java.io.Serializable;

public class Videojuego implements Serializable{

	private int numeroJuego;
	private String nombreJuego;
	private String plataforma;
	private double precio;

	public Videojuego() {

	}

	public Videojuego(int numeroJuego, String nombreJuego, String plataforma, double precio) {
		this.numeroJuego = numeroJuego;
		this.nombreJuego = nombreJuego;
		this.plataforma = plataforma;
		this.precio = precio;
	}

	public int getNumeroJuego() {
		return numeroJuego;
	}

	public void setNumeroJuego(int numeroJuego) {
		this.numeroJuego = numeroJuego;
	}

	public String getNombreJuego() {
		return nombreJuego;
	}

	public void setNombreJuego(String nombreJuego) {
		this.nombreJuego = nombreJuego;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Videojuego [numeroJuego=" + numeroJuego + ", nombreJuego=" + nombreJuego + ", plataforma=" + plataforma
				+ ", precio=" + precio + "]";
	}

}
