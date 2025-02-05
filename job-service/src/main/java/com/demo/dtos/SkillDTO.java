package com.demo.dtos;

public class SkillDTO {
    private Integer id;
    private String name;
    private Integer skillParentId;
    private boolean status;

    // Constructor không tham số
    public SkillDTO() {
    }

    // Constructor có tham số
    public SkillDTO(Integer id, String name, Integer skillParentId, boolean status) {
        this.id = id;
        this.name = name;
        this.skillParentId = skillParentId;
        this.status = status;
    }

    // Getter & Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSkillParentId() {
        return skillParentId;
    }

    public void setSkillParentId(Integer skillParentId) {
        this.skillParentId = skillParentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // toString() để debug/log dễ dàng
    @Override
    public String toString() {
        return "SkillDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skillParentId=" + skillParentId +
                ", status=" + status +
                '}';
    }
}
