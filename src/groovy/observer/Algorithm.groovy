package observer

class Algorithm extends Observable implements Runnable {

    private def result
    private def some

    Algorithm(double start) {
        result = start
        some = Math.random() * 100
    }

    def getResult() {
        result
    }

    @Override
    void run() {
        some.times {
            result *= 1.337
            setStatus(AlgorithmsStatuses.IN_PROGRESS)
        }
        setStatus(AlgorithmsStatuses.DONE)
    }

    private setStatus(AlgorithmsStatuses status) {
        setChanged()
        notifyObservers(status)
    }
}
