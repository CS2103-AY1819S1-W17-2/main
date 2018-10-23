package guitests.guihandles;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.modsuni.model.module.Module;

/**
 * Provides a handle to a module card in the module list panel.
 */
public class ModuleCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String CODE_FIELD_ID = "#code";
    private static final String DEPARTMENT_FIELD_ID = "#department";
    private static final String TITLE_FIELD_ID = "#title";
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String CREDIT_FIELD_ID = "#credit";
    private static final String AVAILABILITY_FIELD_ID = "#availability";
    private static final String SEM1 = "Sem1";
    private static final String SEM2 = "Sem2";
    private static final String SPECIAL_TERM1 = "SpecialTerm1";
    private static final String SPECIAL_TERM2 = "SpecialTerm2";

    private final Label idLabel;
    private final Label codeLabel;
    private final Label departmentLabel;
    private final Label titleLabel;
    private final Label descriptionLabel;
    private final Label creditLabel;
    private final List<Label> availabilityLabels;

    public ModuleCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        codeLabel = getChildNode(CODE_FIELD_ID);
        departmentLabel = getChildNode(DEPARTMENT_FIELD_ID);
        titleLabel = getChildNode(TITLE_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        creditLabel = getChildNode(CREDIT_FIELD_ID);

        Region availabilityContainer = getChildNode(AVAILABILITY_FIELD_ID);
        availabilityLabels = availabilityContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Label.class::cast)
                .collect(Collectors.toList());
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getCode() {
        return codeLabel.getText();
    }

    public String getDepartment() {
        return departmentLabel.getText();
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getCredit() {
        return creditLabel.getText();
    }

    public List<String> getAvailability() {
        return availabilityLabels
                .stream()
                .map(Label::getText)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if this handle contains {@code module}.
     */
    public boolean equals(Module module) {
        return getCode().equals(module.getCode().code)
                && getDepartment().equals(module.getDepartment())
                && getTitle().equals(module.getTitle())
                && getDescription().equals(module.getDescription())
                && getCredit().equals(String.valueOf(module.getCredit()))
                && module.isAvailableInSem1() == getAvailability().contains(SEM1)
                && module.isAvailableInSem2() == getAvailability().contains(SEM2)
                && module.isAvailableInSpecialTerm1() == getAvailability().contains(SPECIAL_TERM1)
                && module.isAvailableInSpecialTerm2() == getAvailability().contains(SPECIAL_TERM2);
    }
}
