package com.example.organizationsapi.models;

import com.example.usersapi.models.User;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "ORGANIZATIONS")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<User> users;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "BROOKLYN")
    private Boolean brooklyn;

    @Column(name = "BRONX")
    private Boolean bronx;

    @Column(name = "MANHATTAN")
    private Boolean manhattan;

    @Column(name = "QUEENS")
    private Boolean queens;

    @Column(name = "STATEN_ISLAND")
    private Boolean statenIsland;

    @Column(name = "FAX")
    private BigInteger fax;

    @Column(name = "URL")
    private String url;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @Column(name = "NON_PROFIT")
    private Boolean nonProfit;

    @Column(name = "FAITH_BASED")
    private Boolean faithBased;

    @Column(name = "NYC_AGENCY")
    private Boolean nycAgency;

    @Column(name = "OTHER_GOVT_ORGANIZATION")
    private Boolean otherGovtOrganization;

    @Column(name = "IS_REGISTERED_WITH_ATTORNEY_GENERAL")
    private Boolean isRegisteredWithAttorneyGeneral;

    @Column(name = "AGING")
    private Boolean aging;

    @Column(name = "ANTI_DISCRIMINATION")
    private Boolean antiDiscrimination;

    @Column(name = "ARTS_AND_CULTURE")
    private Boolean artsAndCulture;

    @Column(name = "BUSINESS")
    private Boolean business;

    @Column(name = "CHILD_CARE_AND_PARENT")
    private Boolean childCareAndParent;

    @Column(name = "COMMUNITY_SERVICE")
    private Boolean communityService;

    @Column(name = "COUNSELLING_AND_SUPPORT_GROUP")
    private Boolean counsellingAndSupportGroup;

    @Column(name = "DISABILITIES")
    private Boolean disabilities;

    @Column(name = "DOMESTIC_VIOLENCE")
    private Boolean domesticViolence;

    @Column(name = "EDUCATION")
    private Boolean education;

    @Column(name = "EMPLOYMENT_AND_JOB_TRAINING")
    private Boolean employmentAndJobTraining;

    @Column(name = "HEALTH")
    private Boolean health;

    @Column(name = "HOMELESSNESS")
    private Boolean homelessness;

    @Column(name = "HOUSING")
    private Boolean housing;

    @Column(name = "IMMIGRATION")
    private Boolean immigration;

    @Column(name = "LEGAL_SERVICES")
    private Boolean legalServices;

    @Column(name = "LGBT")
    private Boolean lgbt;

    @Column(name = "PERSONAL_FINANCE_EDUCATION")
    private Boolean personalFinanceEducation;

    @Column(name = "PROFESSIONAL_ASSOCIATION")
    private Boolean professionalAssociation;

    @Column(name = "VETERANS_AND_MILITARY")
    private Boolean veteransAndMilitary;

    @Column(name = "VICTIMS_LEGAL_SERVICES")
    private Boolean victimsLegalServices;

    @Column(name = "WOMENS_GROUPS")
    private Boolean womensGroups;

    @Column(name = "YOUTH_SERVICES")
    private Boolean youthServices;

    @Column(name = "PHONE")
    private BigInteger phone;

    public Organization (
            String organizationName,
            Boolean brooklyn,
            Boolean bronx,
            Boolean manhattan,
            Boolean queens,
            Boolean statenIsland,
            BigInteger fax,
            String url,
            String description,
            Double latitude,
            Double longitude,
            Boolean nonProfit,
            Boolean faithBased,
            Boolean nycAgency,
            Boolean otherGovtOrganization,
            Boolean isRegisteredWithAttorneyGeneral,
            Boolean aging,
            Boolean antiDiscrimination,
            Boolean artsAndCulture,
            Boolean business,
            Boolean childCareAndParent,
            Boolean communityService,
            Boolean counsellingAndSupportGroup,
            Boolean disabilities,
            Boolean domesticViolence,
            Boolean education,
            Boolean employmentAndJobTraining,
            Boolean health,
            Boolean homelessness,
            Boolean housing,
            Boolean immigration,
            Boolean legalServices,
            Boolean lgbt,
            Boolean personalFinanceEducation,
            Boolean professionalAssociation,
            Boolean veteransAndMilitary,
            Boolean victimsLegalServices,
            Boolean womensGroups,
            Boolean youthServices,
            BigInteger phone) {
        this.organizationName = organizationName;
        this.brooklyn = brooklyn;
        this.bronx = bronx;
        this.manhattan = manhattan;
        this.bronx = queens;
        this.statenIsland = statenIsland;
        this.fax = fax;
        this.url = url;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nonProfit = nonProfit;
        this.faithBased = faithBased;
        this.nycAgency = nycAgency;
        this.otherGovtOrganization = otherGovtOrganization;
        this.isRegisteredWithAttorneyGeneral = isRegisteredWithAttorneyGeneral;
        this.aging = aging;
        this.antiDiscrimination = antiDiscrimination;
        this.artsAndCulture = artsAndCulture;
        this.business = business;
        this.childCareAndParent = childCareAndParent;
        this.communityService = communityService;
        this.counsellingAndSupportGroup = counsellingAndSupportGroup;
        this.disabilities = disabilities;
        this.domesticViolence = domesticViolence;
        this.education = education;
        this.employmentAndJobTraining = employmentAndJobTraining;
        this.health = health;
        this.homelessness = homelessness;
        this.housing = housing;
        this.immigration = immigration;
        this.legalServices = legalServices;
        this.lgbt = lgbt;
        this.personalFinanceEducation = personalFinanceEducation;
        this.professionalAssociation = professionalAssociation;
        this.veteransAndMilitary = veteransAndMilitary;
        this.victimsLegalServices = victimsLegalServices;
        this.womensGroups = womensGroups;
        this.youthServices = youthServices;
        this.phone = phone;
    }
}