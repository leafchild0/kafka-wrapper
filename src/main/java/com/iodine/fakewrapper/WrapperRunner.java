package com.iodine.fakewrapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author victor
 * @date 10/17/19
 */
@Component
public class WrapperRunner implements CommandLineRunner
{
	private final MessageSender sender;

	public WrapperRunner(MessageSender sender)
	{
		this.sender = sender;
	}

	@Override
	public void run(String... args)
	{
		// 0 argument should be a path to file to load data
		if (args.length < 1)
		{
			System.out.println("File path is not passed as an argument to the app");
			System.out.println("Please specify a path to the file with data");

			System.exit(1);
		}

		DataParser parser = new DataParser(args[0]);
		parser.parseData().forEach(sender::sendMessage);
	}
}
