/***********************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Actuate Corporation - initial API and implementation
 ***********************************************************************/
package org.eclipse.birt.chart.ui.swt.series;

import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.ui.swt.composites.DataDefinitionComposite;
import org.eclipse.birt.chart.ui.swt.interfaces.ISeriesUIProvider;
import org.eclipse.birt.chart.ui.swt.interfaces.IUIServiceProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Actuate Corporation
 *  
 */
public class PieSeriesUIProvider implements ISeriesUIProvider
{
    private static final String SERIES_CLASS = "org.eclipse.birt.chart.model.type.impl.PieSeriesImpl";

    /**
     *  
     */
    public PieSeriesUIProvider()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.birt.chart.ui.swt.interfaces.ISeriesUIProvider#getSeriesAttributeSheet(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.birt.chart.model.component.Series)
     */
    public Composite getSeriesAttributeSheet(Composite parent, Series series)
    {
        return new PieSeriesAttributeComposite(parent, SWT.NONE, series);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.birt.chart.ui.swt.interfaces.ISeriesUIProvider#getSeriesDataSheet(org.eclipse.swt.widgets.Composite)
     */
    public Composite getSeriesDataSheet(Composite parent, SeriesDefinition seriesdefinition,
        IUIServiceProvider builder, Object oContext)
    {
        Query query = null;
        if (seriesdefinition.getDesignTimeSeries().getDataDefinition().size() > 0)
        {
            query = ((Query) seriesdefinition.getDesignTimeSeries().getDataDefinition().get(0));
        }
        else
        {
            query = QueryImpl.create("");
            seriesdefinition.getDesignTimeSeries().getDataDefinition().add(query);
        }

        String sPrefix = "";
        // If container of container is chart, it is a base series
        if (seriesdefinition.eContainer().eContainer() instanceof Chart)
        {
            sPrefix = "Base ";
        }
        else
        {
            sPrefix = "Orthogonal ";
        }

        String sTitle = query.getDefinition();
        if (sTitle == null || "".equals(sTitle))
        {
            sTitle = sPrefix + "Series Definition";
        }
        else
        {
            sTitle = sPrefix + "Series Definition (" + sTitle + ")";
        }

        return new DataDefinitionComposite(parent, SWT.NONE, query, seriesdefinition, builder, oContext, sTitle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.birt.chart.ui.swt.interfaces.ISeriesUIProvider#getSeriesClass()
     */
    public String getSeriesClass()
    {
        return SERIES_CLASS;
    }
}