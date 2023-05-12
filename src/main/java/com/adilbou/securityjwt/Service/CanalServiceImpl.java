package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.Entities.*;
import com.adilbou.securityjwt.Repositories.CanalMessagerieRepository;
import com.adilbou.securityjwt.Repositories.UserRepository;
import com.adilbou.securityjwt.Enumration.TYPE_MSG;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CanalServiceImpl implements CanalService{

    private UserRepository userRepository;
//    private OffreRepository offreRepository;
    private CanalMessagerieRepository canalMessagerieRepository;

    public CanalServiceImpl(UserRepository userRepository, CanalMessagerieRepository canalMessagerieRepository) {
        this.userRepository = userRepository;
        this.canalMessagerieRepository = canalMessagerieRepository;
    }

    @Override
    public List<Message> getMssgByDepart(Integer idReceiver) {
        List<Message> messagesSelected = new ArrayList<>();

        User user = (User) userRepository.findById(idReceiver).orElse(null);

        if(user.getRoles().get(0).getRolename().equals("ENSE")) {
            Member member = (Member) userRepository.findById(idReceiver).orElse(null);

            String departement = member.getDepartement();

            if (member != null && !member.getRoles().get(0).getRolename().equals("CHDP")) {
                List<Message> messages = canalMessagerieRepository.findByTypeMsg("ONE_TO_DEP");


                for (Message message : messages) {

                    Member member1 = (Member) userRepository.findById(message.getSrc().getId()).orElse(null);

                    if (member1 != null) {
                        if (member1.getDepartement().equals(departement)) {

                            messagesSelected.add(message);
                        }
                    }
                }
            }
        }else if(user.getRoles().get(0).getRolename().equals("FOUR")){

            List<Message> messages = canalMessagerieRepository.findByTypeMsg("ONE_TO_ONE");

            for (Message message : messages) {

                Fournisseur fournisseur = (Fournisseur) userRepository.findById(idReceiver).orElse(null);
                if(fournisseur.getId().equals(message.getDest().getId())){
                    messagesSelected.add(message);
                }
            }

            List<Message> messagesToAllFour = canalMessagerieRepository.findByTypeMsg("ONE_TO_ALL_FOUR");

            for (Message message : messages) {

                Fournisseur fournisseur = (Fournisseur) userRepository.findById(idReceiver).orElse(null);

//                Offre offre = offreRepository.findByIdAppelOffreAndIdFournisseur(message.getIdAppelOffre(),Long.parseLong( String.valueOf(idReceiver)));
//
//                if(offre !=null){
//                    messagesSelected.add(message);
//                }


            }


        }

        return messagesSelected;
    }




    @Override
    public Message sendMessage(Message message) {

        Message message1 = new Message();

        if(message.getTypeMsg().equals("ONE_TO_ONE")){

            User dest = userRepository.findById(message.getDest().getId()).orElse(null);
            User src = userRepository.findById(message.getSrc().getId()).orElse(null);
            if (dest!=null && src !=null){
                message1.setMessage(message.getMessage());
                message1.setSrc(src);
                message1.setDest(dest);
                message1.setSeen(false);
                message1.setIdAppelOffre(-1L);
                message1.setTypeMsg(message.getTypeMsg());
            }

        }else if(message.getTypeMsg().equals("ONE_TO_DEP")){

            User dest = userRepository.findById(message.getSrc().getId()).get();
            User src = userRepository.findById(message.getSrc().getId()).get();
            if (dest!=null && src !=null){
                message1.setMessage(message.getMessage());
                message1.setSrc(src);
                message1.setDest(dest);
                message1.setSeen(false);
                message1.setIdAppelOffre(-1L);
                message1.setTypeMsg(message.getTypeMsg());

            }

        }else if(message.getTypeMsg().equals("ONE_TO_ALL_FOUR")){

            User dest = userRepository.findById(message.getSrc().getId()).orElse(null);
            User src = userRepository.findById(message.getSrc().getId()).orElse(null);

            if (dest!=null && src !=null){
                message1.setMessage(message.getMessage());
                message1.setSrc(src);
                message1.setDest(dest);
                message1.setSeen(false);
                message1.setIdAppelOffre(message.getIdAppelOffre());
                message1.setTypeMsg(message.getTypeMsg());

            }
        }

         return canalMessagerieRepository.save(message1);
    }


}
