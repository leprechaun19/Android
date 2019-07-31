package com.leprechaun.airport.data.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root
public class ArrayXmlAirline {

    @ElementList(name = "Airline", required = false, inline = true)
    private List<Airline> data;

    public ArrayXmlAirline(){
        data = new ArrayList<>();
    }

    public void setData(ArrayList<Airline> data) {
        this.data = data;
    }

    public List<Airline> getData() {
        return data;
    }

    public void addData(Airline item ) {
        data.add(item);
    }

    public class Airline {

        @Element(name = "AirlineID", required = false)
        private String AirlineID;

        @Element(name = "AirlineFullName", required = false)
        private String AirlineFullName;


        public String getAirlineID() {
            return AirlineID;
        }

        public String getAirlineFullName() {
            return AirlineFullName;
        }
    }
}
