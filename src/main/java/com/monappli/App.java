package com.monappli;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Chrono chrono = new Chrono();
         chrono.start();
      try {
        Thread.sleep(1000);
        chrono.halt();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

    }
}
