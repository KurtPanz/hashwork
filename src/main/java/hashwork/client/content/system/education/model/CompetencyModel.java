package hashwork.client.content.system.education.model;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/09/05.
 */
public class CompetencyModel implements Serializable, Comparable<CompetencyModel> {
    private static final long serialVersionUID = 1L;

    //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
    private String name;
    private String competencyTypeId;
    private String notes;

    @Override
    public int compareTo(CompetencyModel o) {
        return name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompetencyTypeId() {
        return competencyTypeId;
    }

    public void setCompetencyTypeId(String competencyTypeId) {
        this.competencyTypeId = competencyTypeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}