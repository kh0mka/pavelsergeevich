import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class House {
    private final List<ElectricalAppliance> appliances;

    public House() {
        this.appliances = new ArrayList<>();
    }

    public void addAppliance(ElectricalAppliance appliance) {
        appliances.add(appliance);
    }

    public int getTotalPower() {
        return appliances.stream()
                .filter(ElectricalAppliance::isPluggedIn)
                .mapToInt(ElectricalAppliance::getPower)
                .sum();
    }

    public void plugInAppliance(ElectricalAppliance appliance) {
        appliance.plugIn();
    }

    // public void unplugAppliance(ElectricalAppliance appliance) {
    //    appliance.unplug();
    // }

    public List<ElectricalAppliance> getAppliancesSortedByPowerAscending() {
        return appliances.stream()
                .sorted(Comparator.comparingInt(ElectricalAppliance::getPower))
                .collect(Collectors.toList());
    }

    public List<ElectricalAppliance> getAppliancesSortedByPowerDescending() {
        return appliances.stream()
                .sorted(Comparator.comparingInt(ElectricalAppliance::getPower).reversed())
                .collect(Collectors.toList());
    }

    public List<ElectricalAppliance> getPluggedInAppliances() {
        return appliances.stream()
                .filter(ElectricalAppliance::isPluggedIn)
                .collect(Collectors.toList());
    }

    public List<ElectricalAppliance> getUnpluggedAppliances() {
        return appliances.stream()
                .filter(appliance -> !appliance.isPluggedIn())
                .collect(Collectors.toList());
    }
}