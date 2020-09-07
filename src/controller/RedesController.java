package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	public String ip(String SO) {
		StringBuffer bufferR = new StringBuffer("");
		String retorno;
		String aux = "";
		if(SO.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("ipconfig");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();
				while(line != null) {
					if(line.contains("Adaptador")) {
						String [] arg = line.split(":");
						aux = arg[0];
					}
					if(line.contains("IPv4")) {
						String [] arg = line.split(" ");
						bufferR.append(aux + " - Ipv4: " + arg[arg.length - 1]);
						bufferR.append("\n");
					}
					line = buffer.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				Process p = Runtime.getRuntime().exec("ifconfig");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();
				while(line != null) {
					if(line.contains("flags")) {
						String [] arg = line.split(":");
						aux = arg[0];
					}
					if(line.contains("inet")) {
						String [] arg = line.split(" ");
						bufferR.append(aux + " - Ipv4: " + arg[0]);
						bufferR.append("\n");
					}
					line = buffer.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		retorno = bufferR.toString();
		return retorno;
	}
	
	public String ping(String SO) {
		String retorno = "";
		String aux = "";
		if(SO.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("ping 8.8.8.8 -n 10");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();
				while(line!=null) {
					aux = line;
					line = buffer.readLine();
				}
				String [] arg = aux.split(" ");
				retorno = "Media de pings: " + arg[arg.length - 1];
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				double calc = 0;
				Process p = Runtime.getRuntime().exec("ping -c 10 8.8.8.8");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();
				while(line!=null) {
					if(line.contains("ttl")) {
						String [] arg = line.split(" ");
						String [] arg1 = arg[6].split("=");
						calc += Double.parseDouble(arg1[1]);
					}
					line = buffer.readLine();
				}
				calc = calc/10;
				retorno = "Media de pings: " + calc + " ms";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
}
