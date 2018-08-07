package com.tdsi.projet;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Filtre extends javax.swing.filechooser.FileFilter
{
	String[] extensions;
	String description;
	
	public Filtre(String[] extensions, String description)
	{
		super();
		
		this.extensions = extensions;
		this.description = description;
	}
	
	// comme FileFilter est abstraite
	// on red�finit ses m�thodes :
	
	public boolean accept(File fichier)
	{
		if (fichier.isDirectory())
			return true;
		
		String extension = null;
		String nomFichier = fichier.getName();
		
		int i = nomFichier.lastIndexOf('.'); // dernier . avant l'extension
		
		if (i > 0 && i < nomFichier.length()-1)
		{
			extension = nomFichier.substring(i+1, nomFichier.length());
		
			// On v�rifie que l'extension est bien autoris�e :
			for(int j = 0 ; j < extensions.length ; j++)
				if (extension.toLowerCase().equals(this.extensions[j].toLowerCase()))
					return true;
		
			// Si on arrive ici, c'est que l'on n'a pas trouv� l'extension demand�e :
			return false;
		}
		
		return false;
	}
	
	public String getDescription()
	{
		return this.description;
	}
}