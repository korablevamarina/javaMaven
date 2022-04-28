package ru.geekbrains.qa.lesson5;

import okhttp3.Interceptor;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Logger;

class LoggingInterceptor implements Interceptor {

    Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

    @NotNull
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        logger.info(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        okhttp3.Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        logger.info(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
