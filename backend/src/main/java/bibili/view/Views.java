package bibili.view;

public class Views {

	
	public class Common{}
//		public class Salle extends Common{}
//			public class SalleWithHistorique extends Salle{}
//		
//		public class Filiere extends Common {}
//			public class FiliereWithModules extends Filiere {}
//			public class FiliereWithStagiaires extends Filiere {}
//		
//		public class Formateur extends Common {}
//				public class FormateurWithModules extends Formateur {}
		
		public class Auteur extends Common {}
			public class AuteurWithLivres extends Auteur {}
			
		public class Editeur extends Common {}
			public class EditeurWithLivres extends Editeur {}
			
		public class Collection extends Common {}
			public class CollectionWithLivres extends Collection {}
			
		public class Livre extends Common {}
		
}
