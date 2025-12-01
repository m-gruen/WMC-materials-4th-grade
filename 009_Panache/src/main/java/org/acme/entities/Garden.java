package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@NamedQuery(
        name = "Garden.sortedByAddress",
        query = "FROM Garden g ORDER BY g.address ASC"
)
public class Garden extends PanacheEntity {
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @OneToMany(mappedBy = "garden")
    private Collection<Plot> plots;

    public Collection<Plot> getPlots() {
        return plots;
    }

    public void setPlots(Collection<Plot> plots) {
        this.plots = plots;
    }

    // Convenience-method 1.
    public void addPlot(Plot plot) {
        plot.setGarden(this);
    }

    // Convenience-method 2.
    public void removePlot(Plot plot) {
        plot.setGarden(null);
    }
}
