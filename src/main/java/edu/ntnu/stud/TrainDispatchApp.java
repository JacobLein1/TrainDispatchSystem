package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {

  /**.
   * Main method
   * 
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();
    
    ui.init();

    ui.start();

   
  }
}
