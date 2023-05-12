package com.adilbou.securityjwt.Entities;
import com.adilbou.securityjwt.Enumration.TYPE_MSG;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User src;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private User dest;

    private Long idAppelOffre;

    private String message;
    private boolean isSeen;
//    @Enumerated(EnumType.STRING)
//    private TYPE_MSG typeMsg;
    private String typeMsg;

}
