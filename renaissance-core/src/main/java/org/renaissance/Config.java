package org.renaissance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Config {
  private List<String> benchmarkList;
  private int repetitions;
  private List<Plugin> plugins;
  private String policy;
  private boolean readme;

  public Config() {
    this.benchmarkList = new ArrayList<>();
    this.repetitions = -1;
    this.plugins = new ArrayList<>();
    this.policy = "fixed";
    this.readme = false;
  }

  public List<String> benchmarkList() {
    return benchmarkList;
  }

  public int repetitions() {
    return repetitions;
  }

  public List<Plugin> plugins() {
    return plugins;
  }

  public String policy() {
    return policy;
  }

  public boolean readme() {
    return readme;
  }

  public Config copy() {
    Config c = new Config();
    c.benchmarkList = this.benchmarkList;
    c.repetitions = this.repetitions;
    c.plugins = this.plugins;
    c.policy = this.policy;
    c.readme = this.readme;
    return c;
  }

  public Config withBenchmarkSpecification(String v) {
    Config c = copy();
    c.benchmarkList = Arrays.stream(v.split(",")).collect(Collectors.toList());
    return c;
  }

  public Config withPlugins(String v) {
    Config c = copy();
    c.plugins = Arrays.stream(v.split(","))
      .map(n -> {
        try {
          return (Plugin) Class.forName(v).newInstance();
        } catch (Throwable e) {
          throw new RuntimeException(e);
        }
      })
      .collect(Collectors.toList());
    return c;
  }

  public Config withRepetitions(int x) {
    Config c = copy();
    c.repetitions = x;
    return c;
  }

  public Config withPolicy(String policy) {
    Config c = copy();
    c.policy = policy;
    return c;
  }

  public Config withReadme(boolean readme) {
    Config c = copy();
    c.readme = readme;
    return c;
  }
}
