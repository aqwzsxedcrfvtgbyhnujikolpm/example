package data;

public enum Status {
    
    STOP_SCRIPT {
        @Override
        public String toString() {
            return "Stopping script";
        }
    },
    GET_BEST {
        @Override
        public String toString() {
            return "Getting best";
        }
    }
    
}
