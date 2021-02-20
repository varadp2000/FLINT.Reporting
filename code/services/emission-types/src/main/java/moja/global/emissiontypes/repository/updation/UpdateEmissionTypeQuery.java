/*
 * Copyright (C) 2021 Moja Global
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package moja.global.emissiontypes.repository.updation;

import moja.global.emissiontypes.configurations.DatabaseConfig;
import moja.global.emissiontypes.models.EmissionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @since 1.0
 * @author Kwaje Anthony <tony@miles.co.ke>
 * @version 1.0
 */
@Component
@Slf4j
public class UpdateEmissionTypeQuery {

	@Autowired
	DatabaseConfig databaseConfig;

	/**
	 * Updates a emissionType record
	 * @param emissionType a bean containing the record's details
	 * @return the number of records affected by the query i.e updated
	 */
	public Mono<Integer> updateEmissionType(EmissionType emissionType){
		
		log.trace("Entering updateEmissionType()");

		String query = "UPDATE emission_type SET name = ?, abbreviation = ?, description = ? WHERE id = ?";

		return
			Mono.from(
				databaseConfig
					.getDatabase()
					.update(query)
					.parameters(
							emissionType.getName(),
							emissionType.getAbbreviation(),
							emissionType.getDescription(),
							emissionType.getId())
					.counts());
	}
}
