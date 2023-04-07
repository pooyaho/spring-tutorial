package ir.mapsa.secondspringproject.codegenerator;

@MyMapper
public interface SampleConverter {

    SampleEntity convert(SampleDto dto);

    SampleDto convert(SampleEntity dto);
}
