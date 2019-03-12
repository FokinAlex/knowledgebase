package observer

class AlgorithmsObserver implements Observer {

    @Override
    void update(Observable algorithm, Object status) {
        if (status instanceof AlgorithmsStatuses && algorithm instanceof Algorithm) {
            switch (status) {
                case AlgorithmsStatuses.PENDING:
                    println "${this}:\t${algorithm}\tis waiting..."
                    break

                case AlgorithmsStatuses.IN_PROGRESS:
                    println "${this}:\t${algorithm}\tupdates value -> ${algorithm.getResult()}"
                    break

                case AlgorithmsStatuses.DONE:
                    println "${this}:\t${algorithm}\tends with result ${algorithm.getResult()}"
                    break

                default:
                    throw new IllegalArgumentException("Illegal algorithm status")
            }
        }
    }
}
