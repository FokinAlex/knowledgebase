package observer

class GMain {

    static void main(String[] args) {

        AlgorithmsObserver observer = new AlgorithmsObserver()

        def algorithms = [
                new Algorithm(100.2),
                new Algorithm(-432),
                new Algorithm(0),
                new Algorithm(0.001),
                new Algorithm(1.337),
                new Algorithm(-6000.0005),
                new Algorithm(99999)
        ]

        algorithms*.addObserver(observer)

        def threads = algorithms.stream()
                .map{ algorithm -> new Thread(algorithm) }

        threads*.start();
    }
}
