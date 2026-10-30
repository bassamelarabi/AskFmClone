package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question implements IQuestion{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String content;
    private LocalDate publishedDate;

    @Setter(AccessLevel.NONE) // prevents the external user of this class from accidental modifying the question state without adding/removing answers
    private Boolean isAnswered = false; //this indicates if a question is answered or not yet

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private RegisteredUser sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private RegisteredUser receiver;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;


    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Reaction> reactions;

    public void changeStateAnswered(){
        this.isAnswered = true;
    }
    public void resetState(){
        this.isAnswered = false;
    }




}
