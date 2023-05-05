package gestionrefugeanimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mia.itmf.projet.gestionrefugeanimal.exception.ExceptionAnimal;
import com.mia.itmf.projet.gestionrefugeanimal.model.Animal.Sexe;
import com.mia.itmf.projet.gestionrefugeanimal.model.Chat;
import com.mia.itmf.projet.gestionrefugeanimal.model.Chat.RaceChat;
import com.mia.itmf.projet.gestionrefugeanimal.model.Chien;
import com.mia.itmf.projet.gestionrefugeanimal.model.Chien.RaceChien;
import com.mia.itmf.projet.gestionrefugeanimal.model.Lapin;
import com.mia.itmf.projet.gestionrefugeanimal.model.Lapin.RaceLapin;
import com.mia.itmf.projet.gestionrefugeanimal.model.Refuge;

@TestMethodOrder(MethodOrderer.MethodName.class )
public class TestAnimal {

	private static Refuge gestionAnimal = new Refuge("Manomano","Nantes");
	
	@BeforeAll
	public static void init() {
		
		//Chien
		try {
			gestionAnimal.ajouterAnimal(new Chien("Chouchou", RaceChien.Caniche , 5, Sexe.FEMININ));
			gestionAnimal.ajouterAnimal(new Chien("Pipi", RaceChien.Cocker , 2, Sexe.MAXULIN));
			gestionAnimal.ajouterAnimal(new Chien("Charline", RaceChien.Boledogue , 4, Sexe.FEMININ));
			//Chat
			gestionAnimal.ajouterAnimal(new Chat("Chalotte", RaceChat.Abyssin, 2, Sexe.FEMININ));
			gestionAnimal.ajouterAnimal(new Chat("Jiff", RaceChat.Anatoli, 5, Sexe.MAXULIN));
			//Lapin
			gestionAnimal.ajouterAnimal(new Lapin("Mimi", RaceLapin.Alaska, 6, Sexe.FEMININ));
			gestionAnimal.ajouterAnimal(new Lapin("Memo", RaceLapin.Lynx, 4, Sexe.MAXULIN));
			gestionAnimal.ajouterAnimal(new Lapin("Milano", RaceLapin.Neo_Zelandais, 4, Sexe.MAXULIN));
			
			gestionAnimal.ajouterAnimal(new Chat("Camilou", RaceChat.Asian, 3, Sexe.MAXULIN));
			gestionAnimal.ajouterAnimal(null);
		} catch (ExceptionAnimal e) {
			e.printStackTrace();
		}
	}
	
	//@Disabled
	@Test @Order(1)
	public void test1_afficherAnimal() {
		System.out.println(gestionAnimal.toString());
		gestionAnimal.afficherListeAnimaux();
		try {
			assertEquals(gestionAnimal.retrouverUnAnimal("Milano", null, null, Sexe.MAXULIN).toString(),"Animal [id=Lapin-3, Nom refuge = Manomano, nom Animal=Milano, race=Neo_Zelandais, age=4mois, sexe=MAXULIN, status=DISPONIBLE]");
		} catch (ExceptionAnimal e) {
			e.printStackTrace();
		}
		assertEquals(gestionAnimal.getNombreAnimal(), 9);
		gestionAnimal.afficherListeAnimauxParEspece("CHAT");
	}
	
	//@Disabled
	@Test @Order(2)
	public void test2_ajouterAnimal() {
		Chat chat = new Chat("Milomilo", RaceChat.Burmese, 5, Sexe.MAXULIN);
		try {
			gestionAnimal.ajouterAnimal(chat);
		} catch (ExceptionAnimal e) {
			e.printStackTrace();
		}
		assertEquals(gestionAnimal.getNombreAnimal(), 10);
	}
	
	//@Disabled
	@Test @Order(2)
	public void test3_modifierAnimal() {
		try {
			gestionAnimal.miseAJourAnimal("Chat-4","Minono",null,null);
			assertEquals(gestionAnimal.retrouverUnAnimal("Minono", RaceChat.Burmese, null, null).toString(), "Animal [id=Chat-4, Nom refuge = Manomano, nom Animal=Minono, race=Burmese, age=5mois, sexe=MAXULIN, status=DISPONIBLE]");
		} catch (ExceptionAnimal e) {
			e.printStackTrace();
		 }
		assertEquals(gestionAnimal.getNombreAnimal(), 10);
	}
	
	//@Disabled
	@Test @Order(2)
	public void test3_supprimererAnimal() {
		try {
			gestionAnimal.supprimerAnimal(gestionAnimal.retrouverUnAnimal("Minono", RaceChat.Burmese, 5, null));
		} catch (ExceptionAnimal e) {
				e.printStackTrace();
		}
		assertEquals(gestionAnimal.getNombreAnimal(), 9);
	}
	

}
