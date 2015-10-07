/*
 * #%L
 * Utility classes for working with Java 3D.
 * %%
 * Copyright (C) 2015 Board of Regents of the University of
 * Wisconsin-Madison.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package org.scijava.java3d;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.Service;

/**
 * Default service for working with Java 3D.
 * 
 * @author Curtis Rueden
 */
@Plugin(type = Service.class)
public class DefaultJava3DService extends AbstractService implements
	Java3DService
{

	@Override
	public List<File> getLibExtLocations() {
		final ArrayList<File> files = new ArrayList<File>();
		final String extDirs = System.getProperty("java.ext.dirs");
		if (extDirs == null) return files;
		for (final String dir : extDirs.split(":")) {
			checkLibExtDirectory(files, dir);
		}
		return files;
	}

	// -- Helper methods --

	private void checkLibExtDirectory(final ArrayList<File> files,
		final String dir)
	{
		checkFile(files, new File(dir, "j3dcore.jar"));
		checkFile(files, new File(dir, "vecmath.jar"));
		checkFile(files, new File(dir, "j3dutils.jar"));
	}

	private void checkFile(ArrayList<File> files, File file) {
		if (file.exists()) files.add(file);
	}

}
