package bibili;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import bibili.model.Auteur;
import bibili.service.AuteurService;

@SpringBootApplication
public class BibiliApplication implements CommandLineRunner {

	@Autowired
    private AuteurService auteurService;
	
	public static void main(String[] args) {
		SpringApplication.run(BibiliApplication.class, args);
	}
	
    @Override
    public void run(String... args) {
        Auteur a = new Auteur(null, "nom", "prenom", "fr");

        auteurService.insert(a);
        System.out.println(a);
    }

}
