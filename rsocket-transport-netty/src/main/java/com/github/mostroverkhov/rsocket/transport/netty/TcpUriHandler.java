/*
 * Copyright 2016 Netflix, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.github.mostroverkhov.rsocket.transport.netty;

import com.github.mostroverkhov.rsocket.transport.ClientTransport;
import com.github.mostroverkhov.rsocket.transport.ServerTransport;
import com.github.mostroverkhov.rsocket.transport.netty.client.TcpClientTransport;
import com.github.mostroverkhov.rsocket.transport.netty.server.TcpServerTransport;
import com.github.mostroverkhov.rsocket.uri.UriHandler;
import java.net.URI;
import java.util.Optional;
import reactor.ipc.netty.tcp.TcpServer;

public class TcpUriHandler implements UriHandler {
  @Override
  public Optional<ClientTransport> buildClient(URI uri) {
    if ("tcp".equals(uri.getScheme())) {
      return Optional.of(TcpClientTransport.create(uri.getHost(), uri.getPort()));
    }

    return UriHandler.super.buildClient(uri);
  }

  @Override
  public Optional<ServerTransport> buildServer(URI uri) {
    if ("tcp".equals(uri.getScheme())) {
      return Optional.of(TcpServerTransport.create(TcpServer.create(uri.getHost(), uri.getPort())));
    }

    return UriHandler.super.buildServer(uri);
  }
}
