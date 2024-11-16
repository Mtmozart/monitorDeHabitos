package br.com.monitodehabitos.monitodehabitos.domain.entities;

import java.time.LocalDate;

public class Progress {

    private Long id;
    private LocalDate currentDate;
    private ProgressEnumStatus status;

    public Progress(Long id, LocalDate currentDate, ProgressEnumStatus status) {
        this.id = id;
        this.currentDate = currentDate;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public LocalDate getDate() {
        return currentDate;
    }

    public ProgressEnumStatus getProgressEnumStatus() {
        return status;
    }

    public ProgressEnumStatus changeStatusToCompleteOrNot() {
        if(this.status.equals(ProgressEnumStatus.NOT_STARTED)){
            return this.status = ProgressEnumStatus.COMPLETED;
        } else  {
            return this.status = ProgressEnumStatus.NOT_STARTED;
        }
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", currentDate=" + currentDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Progress progress)) return false;

        if (!getId().equals(progress.getId())) return false;
        if (!getCurrentDate().equals(progress.getCurrentDate())) return false;
        return status == progress.status;
    }
    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCurrentDate().hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
