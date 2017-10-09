/* 
 * Copyright 2013 Universal Bilgi Teknolojileri.
 * 
 * UKL 1.1 lisansı ile lisanslanmıştır. Bu dosyanın lisans koşullarına uygun 
 * olmayan şekilde kullanımı yasaklanmıştır. Lisansın bir kopyasını aşağıdaki 
 * linkten edinebilirsiniz.
 * 
 * http://www.uni-yaz.com/lisans/UKL_1_1.pdf
 * 
 * Yasalar aksini söylemediği veya yazılı bir sözleşme ile aksi belirtilmediği sürece,
 * bu yazılım mevcut hali ile hiç bir garanti vermeden veya herhangi bir şart ileri
 * sürmeden dağıtılır. Bu yazılımın edinim izinleri ve limitler konusunda lisans 
 * sözleşmesine bakınız.
 * 
 */
package com.tr.wordbook.standart;

import org.hibernate.proxy.HibernateProxyHelper;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Cloneable, Serializable, IdBasedDomainObject, BaseModel {

	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (HibernateProxyHelper.getClassWithoutInitializingProxy(obj) != HibernateProxyHelper
					.getClassWithoutInitializingProxy(this) &&
					(!HibernateProxyHelper.getClassWithoutInitializingProxy(obj).isAssignableFrom(this.getClass()))) {
			return false;
		}

		BaseEntity other = (BaseEntity) obj;

		if (getId() == null) {
			return super.equals(obj);
		}

		if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	/**
	 * Seçim alanları için veri içeriğini döndürür.
	 */
	public abstract String toString();

	public Object clone() throws CloneNotSupportedException {

		return (BaseEntity) super.clone();

	}
}
