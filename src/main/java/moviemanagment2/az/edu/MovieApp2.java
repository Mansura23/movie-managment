package moviemanagment2.az.edu;

import az.moviemanagement.Movie;

import java.util.Locale;
import java.util.Scanner;

public class MovieApp2 {
    private static Movie2[] movies = new Movie2[3];
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showOptions();
            Integer input = isValidInput();
            if (input != null) {

                if (input == 0) {
                    break;
                } else if (input == 1) {
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Movie " + (i + 1) + ":");
                        String movieName = sc.next().toLowerCase();
                        System.out.println("Rate " + (i + 1) + ":");
                        double rate = sc.nextDouble();
                        if (rate < 0.0 || rate > 10.0) {
                            System.out.println("Invalid rate ");
                        }
                        movies[i] = new Movie2(movieName, rate);

                    }
                } else if (input == 2) {
                    for (int i = 0; i < movies.length; i++) {
                        if (movies[i].getName() != "deleted") {
                            System.out.println(movies[i].toString());
                        }


                    }
                } else if (input == 3) {
                    enterMovie();

                } else if (input == 4) {
                    getAvgRatingMinAndMax();
                } else if (input == 5) {
                    System.out.println("What movie do you want to search?");
                    String inputName = sc.next();
                    if (findMovieByName(inputName) == 0) {
                        System.out.println("Movie not found");
                    } else {
                        System.out.println(movies[findMovieByName(inputName)].getRating());
                    }
                } else if (input == 6) {
                    System.out.println("What movie do you want to update?");
                    String inputName = sc.next();
                    if (findMovieByName(inputName) == 0.0) {
                        System.out.println("Movie not found");
                    } else {
                        System.out.println("Enter new movie rate ");
                        double newrate = sc.nextDouble();
                        movies[findMovieByName(inputName)].setRating(newrate);
                    }


                } else if (input == 7) {
                    System.out.println("What movie do you want to delete?");
                    String inputName = sc.next();
                    int index = findMovieByName(inputName);

                    if (index == 0) {
                        System.out.println("Movie not found");
                    } else {
                        movies[index].setName("deleted");
                        movies[index].setRating(0.0);

                        System.out.println("Movie deleted");


                    }


                } else if (input == 8) {
                    Selectionsort(movies);

                } else {
                    System.out.println("Invalid input.Please try again");
                }


            }
            else{
                break;

            }
        }


    }

    public static void showOptions() {
        System.out.println("press 1 to enter movies");
        System.out.println("press 2 to display movies");
        System.out.println("press 0 to exit");
        System.out.println("press 3 to enter more movies");
        System.out.println("press 4 to show statistics");
        System.out.println("press 5 to search movies");
        System.out.println("press 6 to update movie ratings");
        System.out.println("press 7 to delete movie");
        System.out.println("press 8 to sort movies");
        System.out.println("Enter your choice: ");
    }

    public static Integer isValidInput() {
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input.");
            return null;

        }
        int input = sc.nextInt();
        if (input < 1 || input > 8) {
            System.out.println("Invalid input.");
            return null;
        }
        return input;
    }

    public static void getAvgRatingMinAndMax() {
        double avarage = 0.0;

        double minrate = 0;
        double maxrate = 0;
        int sum = 0;
        for (int i = 0; i < movies.length; i++) {
            if (maxrate < movies[i].getRating()) {
                maxrate = movies[i].getRating();
                minrate = maxrate;

            }
            sum += movies[i].getRating();

        }
        for (int i = 0; i < movies.length; i++) {
            if (minrate > movies[i].getRating()) {
                minrate = movies[i].getRating();
            }
        }
        avarage = sum / movies.length;
        System.out.println("Average rating is " + avarage);
        for (int i = 0; i < movies.length; i++) {
            if (maxrate == movies[i].getRating()) {
                System.out.println("Max rating is " + movies[i].getName());
            }
        }
        for (int i = 0; i < movies.length; i++) {
            if (minrate == movies[i].getRating()) {
                System.out.println("Min rating is " + movies[i].getName());
            }
        }

    }

    public static int findMovieByName(String name) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].getName().equalsIgnoreCase(name.trim())) {
                return i;
            }


        }
        return 0;

    }

    public static void Selectionsort(Movie2[] movies) {
        for (int i = 0; i < movies.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < movies.length; j++) {
                if (movies[j].getName() != "deleted") {
                    if (movies[j].getRating() < movies[min].getRating()) {
                        min = j;
                    }
                }


            }
            double temp = movies[i].getRating();
            movies[i].setRating(movies[min].getRating());
            movies[min].setRating(temp);

        }
    }

    public static Movie2[] enterMovie() {
        System.out.println("How many movies do you want to add?");
        int input1 = sc.nextInt();


        Movie2[] temp = new Movie2[input1 + movies.length];
        for (int i = 0; i < movies.length; i++) {
            temp[i] = movies[i];

        }

        for (int i = movies.length; i < temp.length; i++) {
            System.out.println("Movie " + (i + 1) + ":");
            String movieName = sc.next().toLowerCase();
            System.out.println("Rate " + (i + 1) + ":");
            double rate = sc.nextDouble();
            temp[i] = new Movie2(movieName, rate);

        }
        movies = new Movie2[input1 + movies.length];
        movies = temp;
        return movies;
    }


}
