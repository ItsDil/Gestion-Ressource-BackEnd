package com.adilbou.securityjwt.Service;


import com.adilbou.securityjwt.DTOs.*;
import com.adilbou.securityjwt.Entities.Panne;

import java.util.List;

public interface PanneService {
		List<Panne> getAllPannes();
	    PanneDTO_Tech panneTechnicien();
	    PanneDTO_Tech panneResponsable();
		RessourceDTO_ENS getRessourcesWithState(Integer idEnse);

		void setConstatPanne(PanneDTO_Constat panneConstatDTO);
		void setPanne(PanneDTO_Ens panneDTOEns);
		void setEtat(Long idPanne);
		List<Panne> getAllPannesForTec();

		List<Panne> getAllPannesForResp();



}
