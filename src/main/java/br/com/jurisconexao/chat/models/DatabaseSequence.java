/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jurisconexao.chat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "chats_database_sequences")
public class DatabaseSequence {

     @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
    private int seq;

    public DatabaseSequence() {
    }

    public DatabaseSequence(Long id, int seq) {
        this.id = id;
        this.seq = seq;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    
}
