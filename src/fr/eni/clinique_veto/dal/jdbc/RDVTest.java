package fr.eni.clinique_veto.dal.jdbc;

import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.dal.DALException;

public class RDVTest {
	public static void main(String[] args) {
//	    java.util.Date utilDate = new java.util.Date();
//	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//	    System.out.println("utilDate:" + utilDate.getTime());
//	    System.out.println("sqlDate:" + sqlDate.getTime());
//	    
//	    java.util.Date test = new java.util.Date(sqlDate.getTime());
//	    
//	    System.out.println(test);
//	    System.out.println(test.getTime());
		
		Personnel p = new Personnel();
		p.setId(1);
		Animal a = new Animal();
		a.setCodeAnimal(3);
		
		RendezVous rdv = new RendezVous(p, new java.util.Date(), a);
		RendezVousDAOJdbcImpl dao = new RendezVousDAOJdbcImpl();
		
		try {
			dao.insert(rdv);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println(new java.util.Date());
//		List<RendezVous> list = null;
//		try {
//			list = dao.getRDVs(p, new java.util.Date());
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		RendezVous r = list.get(0);
//		System.out.println(r.getDate().getTime());
	}
}
