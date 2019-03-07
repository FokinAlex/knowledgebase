package observer

class Algorithm extends Observable implements Runnable {

    private def result
    private def status
    private def some

    Algorithm(double start) {
        result = start
        status = AlgorithmsStatuses.PENDING
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
        this.status = status
        setChanged()
        notifyObservers(status)
    }
}
