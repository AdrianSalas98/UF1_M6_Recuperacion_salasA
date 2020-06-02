import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main_Recuperacion {

	public static int contador = 0;
	public static ArrayList<Videojuego> arrayJuegos;

	public static void main(String[] args) {
		crearFicheroVideojuegos();
		modificarVideojuegos();
		crearXML();

	}

	public static void crearFicheroVideojuegos() {
		Videojuego vj1 = new Videojuego(1, "Terraria", "XBOXOne", 20.99);
		contador++;
		Videojuego vj2 = new Videojuego(2, "God of War 4", "PS4", 45.09);
		contador++;
		Videojuego vj3 = new Videojuego(3, "Mario Kart 8", "Switch", 33.95);
		contador++;

		FileOutputStream fos = null;
		ObjectOutputStream salida = null;

		try {
			fos = new FileOutputStream("videojocs.dat");
			salida = new ObjectOutputStream(fos);

			salida.writeObject(vj1);
			salida.writeObject(vj2);
			salida.writeObject(vj3);

			System.out.println("Fichero creado con exito");
			System.out.println();

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void modificarVideojuegos() {
		Videojuego vj;
		Videojuego vjElegido = null;
		Scanner lector = new Scanner(System.in);
		Scanner lectorint = new Scanner(System.in);
		Scanner lectordouble = new Scanner(System.in);
		boolean numeroValido = false;

		try {
			File f = new File("videojocs.dat");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			FileOutputStream fos = null;
			ObjectOutputStream salida = null;

			arrayJuegos = new ArrayList<Videojuego>();

			for (int i = 0; i < contador; i++) {
				vj = (Videojuego) ois.readObject();
				arrayJuegos.add(vj);
			}

			System.out.println("VideoJuegos en el fichero:");
			System.out.println();
			for (Videojuego videojuego : arrayJuegos) {
				System.out.println(videojuego.toString());
			}

			System.out.println();
			System.out.println("Introduce el numero de videojuego a modificar: ");
			int numModificar = lectorint.nextInt();

			for (Videojuego videojuego : arrayJuegos) {
				if (numModificar == videojuego.getNumeroJuego()) {
					vjElegido = videojuego;
					numeroValido = true;

				}
			}

			if (numeroValido == true) {
				System.out.println("¡Numero correcto!");
				System.out.println();

				System.out.println("¿Quieres cambiar el nombre del videojuego? (SI/NO)");
				String respuestaNombre = lector.nextLine();

				if (respuestaNombre.equalsIgnoreCase("si")) {
					System.out.println();
					System.out.println("Introduce el nuevo nombre");
					String nuevoNombre = lector.nextLine();

					vjElegido.setNombreJuego(nuevoNombre);
				}

				if (!respuestaNombre.equalsIgnoreCase("si") || !respuestaNombre.equalsIgnoreCase("no")) {
					System.err.println("Respuesta incorrecta... Contesta con SI o NO");
				}

				System.out.println("¿Quieres cambiar la plataforma del videojuego? (SI/NO)");
				String respuestaPlataforma = lector.nextLine();

				if (respuestaPlataforma.equalsIgnoreCase("si")) {
					System.out.println();
					System.out.println("Introduce la nueva plataforma");
					String nuevaPlataforma = lector.nextLine();

					vjElegido.setPlataforma(nuevaPlataforma);
				}

				if (!respuestaPlataforma.equalsIgnoreCase("si") || !respuestaPlataforma.equalsIgnoreCase("no")) {
					System.err.println("Respuesta incorrecta... Contesta con SI o NO");
				}

				System.out.println("¿Quieres cambiar el precio del videojuego? (SI/NO)");
				String respuestaPrecio = lector.nextLine();

				if (respuestaPrecio.equalsIgnoreCase("si")) {
					System.out.println();
					System.out.println("Introduce el nuevo precio");
					double nuevoPrecio = lectordouble.nextDouble();

					vjElegido.setPrecio(nuevoPrecio);
				}

				if (!respuestaPrecio.equalsIgnoreCase("si") || !respuestaPrecio.equalsIgnoreCase("no")) {
					System.err.println("Respuesta incorrecta... Contesta con SI o NO");
				}

			} else {
				System.err.println("El videojuego que buscas no existe...");
			}
			
			System.out.println("SI DESCOMENTAS EL CODIGO, EN EL ARCHIVO SE CAMBIA");
			System.out.println("PERO POR UN TEMA DE HEADER ME SALE CORRUPTED EXCEPTION");
			System.out.println("POR USAR EL MISMO LECTOR, NO HE SABIDO SOLUCIONARLO");

//			fos = new FileOutputStream(f);
//			salida = new ObjectOutputStream(fos);
//
//			for (Videojuego videojuego : arrayJuegos) {
//				salida.reset();
//				salida.writeObject(videojuego);
//			}
//
//			System.out.println();
//
//			for (int i = 0; i < contador; i++) {
//				vj = (Videojuego) ois.readObject();
//				System.out.println(vj.toString());
//			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void crearXML() {
		FileInputStream fis = null;
		ObjectInput entrada = null;
		File f = new File("videojocs.dat");
		File f2 = new File("videojocs.xml");

		if (f2.exists()) {
			f2.delete();
		}

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element eRaiz = doc.createElement("Videoconsolas");
			doc.appendChild(eRaiz);

			try {
				fis = new FileInputStream(f);
				entrada = new ObjectInputStream(fis);
				Element eXboxOne;
				Element ePS44;
				Element eSwitch;

				for (int i = 0; i < Main_Recuperacion.arrayJuegos.size(); i++) {
					if (Main_Recuperacion.arrayJuegos.get(i).getPlataforma().equalsIgnoreCase("xboxone")) {
						eXboxOne = doc.createElement("XboxOne");
						eRaiz.appendChild(eXboxOne);
						Element jocs = doc.createElement("Jocs");
						eXboxOne.appendChild(jocs);
						Element juego = doc.createElement("joc");
						Attr atributo = doc.createAttribute("id");
						atributo.setValue(Integer.toString(Main_Recuperacion.arrayJuegos.get(i).getNumeroJuego()));
						juego.setAttributeNode(atributo);
						jocs.appendChild(juego);
						Element nombreJuego = doc.createElement("nom");
						nombreJuego.setTextContent(Main_Recuperacion.arrayJuegos.get(i).getNombreJuego());
						juego.appendChild(nombreJuego);
						Element precioJuego = doc.createElement("preu");
						nombreJuego.setTextContent(Double.toString(Main_Recuperacion.arrayJuegos.get(i).getPrecio()));
						juego.appendChild(precioJuego);
					}

					if (Main_Recuperacion.arrayJuegos.get(i).getPlataforma().equalsIgnoreCase("ps4")) {
						ePS44 = doc.createElement("PS4");
						eRaiz.appendChild(ePS44);
						Element jocs = doc.createElement("Jocs");
						ePS44.appendChild(jocs);
						Element juego = doc.createElement("joc");
						Attr atributo = doc.createAttribute("id");
						atributo.setValue(Integer.toString(Main_Recuperacion.arrayJuegos.get(i).getNumeroJuego()));
						juego.setAttributeNode(atributo);
						jocs.appendChild(juego);
						Element nombreJuego = doc.createElement("nom");
						nombreJuego.setTextContent(Main_Recuperacion.arrayJuegos.get(i).getNombreJuego());
						juego.appendChild(nombreJuego);
						Element precioJuego = doc.createElement("preu");
						nombreJuego.setTextContent(Double.toString(Main_Recuperacion.arrayJuegos.get(i).getPrecio()));
						juego.appendChild(precioJuego);
					}

					if (Main_Recuperacion.arrayJuegos.get(i).getPlataforma().equalsIgnoreCase("switch")) {
						eSwitch = doc.createElement("Switch");
						eRaiz.appendChild(eSwitch);
						Element jocs = doc.createElement("Jocs");
						eSwitch.appendChild(jocs);
						Element juego = doc.createElement("joc");
						Attr atributo = doc.createAttribute("id");
						atributo.setValue(Integer.toString(Main_Recuperacion.arrayJuegos.get(i).getNumeroJuego()));
						juego.setAttributeNode(atributo);
						jocs.appendChild(juego);
						Element nombreJuego = doc.createElement("nom");
						nombreJuego.setTextContent(Main_Recuperacion.arrayJuegos.get(i).getNombreJuego());
						juego.appendChild(nombreJuego);
						Element precioJuego = doc.createElement("preu");
						nombreJuego.setTextContent(Double.toString(Main_Recuperacion.arrayJuegos.get(i).getPrecio()));
						juego.appendChild(precioJuego);
					}
				}

			} catch (Exception e) {
				System.err.println(e);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f2);

			transformer.transform(source, result);

			fis.close();
			entrada.close();
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
