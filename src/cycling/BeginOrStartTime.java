import java.time.LocalTime;

class BeginOrStartTime implements StageTime {
    private LocalTime time;

    public BeginOrStartTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
    
}