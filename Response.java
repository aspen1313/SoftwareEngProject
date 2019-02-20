package com.example.projectpart1_as;

//Each response is an option for the election that people can vote for
public class Response {
    //Attributes
    //The option displayed to user
    String answer;

    //keeps track of how many times people voted for this option
    int voteTally;

    //Will be set by Election class when an election has been frozen
    boolean isFrozen;


    //Methods
    public Response(String ans){
        answer = ans;
        voteTally=0;
    }

    //Display the results of this option by returning the option value
    public String getAnswer(){
        return answer;
    }

    //Display the results of this option by returning the tally of votes for this option
    public int getTally(){
        return voteTally;
    }

    //Set methods
    public void setAnswer(String ans){
        answer = ans;
    }

    public void setVoteTally(int vote){
        voteTally = vote;
    }

    public void setisFrozen(boolean freeze){
        isFrozen = freeze;
    }

    //keep track of votes for this option
    //Cannot increase vote count if elections is frozen
    public void vote(){
      if (!(isFrozen))
        voteTally++;
      return;
    }
}
