/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.kenyaemr.page.controller.reports;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.module.kenyacore.report.ReportManager;
import org.openmrs.module.kenyaemr.EmrConstants;
import org.openmrs.module.kenyacore.report.ReportBuilder;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;

/**
 * Homepage for the reports app
 */
@AppPage(EmrConstants.APP_REPORTS)
public class ReportsHomePageController {

	public void controller(PageModel model, @SpringBean ReportManager reportManager) {
		model.addAttribute("mohReports", getReportDefinitionSummaries(reportManager, "moh"));
		model.addAttribute("facilityReports", getReportDefinitionSummaries(reportManager, "facility"));
	}

	/**
	 * Fetches all definition summaries for reports with a given tag
	 * @param tag the report tag
	 * @return the definition summaries
	 */
    private List<SimpleObject> getReportDefinitionSummaries(ReportManager reportManager, String tag) {
    	List<SimpleObject> ret = new ArrayList<SimpleObject>();
		for (ReportBuilder reportBuilder : reportManager.getReportBuildersByTag(tag)) {
			ret.add(SimpleObject.create("name", reportBuilder.getReportDefinitionSummary().getName(), "builder", reportBuilder.getClass().getName()));
		}
		return ret;
    }
}