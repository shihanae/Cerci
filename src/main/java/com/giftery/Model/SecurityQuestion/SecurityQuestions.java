package com.giftery.Model.SecurityQuestion;

import com.giftery.Model.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "security_questions")
public class SecurityQuestions
{
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceIdGenerator") // Can this be the same for every entity?
    @SequenceGenerator(name="SequenceIdGenerator", sequenceName = "security_questions_seq", allocationSize = 1)
    @Getter
    @Setter
    private long id;

    @Column(name = "question_one", nullable = false, length = 2)
    @Getter
    @Setter
    private int questionOne;

    @Column(name = "question_two", nullable = false, length = 2)
    @Getter
    @Setter
    private int questionTwo;

    @Column(name = "answer_one", nullable = false, length = 2)
    @Getter
    @Setter
    private int answerOne;

    @Column(name = "answer_two", nullable = false, length = 2)
    @Getter
    @Setter
    private int answerTwo;

    @OneToOne
    @JoinColumn(name="user_id")
    @Getter
    @Setter
    private User user;
}
