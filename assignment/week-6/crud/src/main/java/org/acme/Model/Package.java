package org.acme.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

@Entity
@Table(name = "package", schema="packages")
public class Package extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "Name")
    private String name;
    
    @Column(name = "Architecture")
    private String arch;

    @Column(name = "Description")
    private String desc;
    
    @Column(name = "URL")
    private String url;

    @Column(name = "Maintainer")
    private String maintainer;

    @Column(name = "License")
    private String license;
    
    public Package() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    // Untuk Builder Pattern
    private Package(PackageBuilder builder) {
        this.name = builder.name;
        this.arch = builder.arch;
        this.desc = builder.desc;
        this.url = builder.url;
        this.maintainer = builder.maintainer;
        this.license = builder.license;
	}

    public static class PackageBuilder {
        private String name;
        private String arch;
        private String desc;
        private String url;
        private String maintainer;
        private String license;

        public PackageBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PackageBuilder setArch(String arch) {
            this.arch = arch;
            return this;
        }

        public PackageBuilder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public PackageBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public PackageBuilder setMaintainer(String maintainer) {
            this.maintainer = maintainer;
            return this;
        }

        public PackageBuilder setLicense(String license) {
            this.license = license;
            return this;
        }

        public Package build() {
			return new Package(this);
		}
    }

}
